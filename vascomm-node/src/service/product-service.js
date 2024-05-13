import {prismaClient} from "../application/database.js";
import {ResponseError} from "../error/response-error.js";


const checkProductMustExists = async (user, productId) => {
    const product = await prismaClient.m_product.findFirst({
        where: {
            product_id: productId,
            deleted_by: null
        }
    });

    if (!product) {
        throw new ResponseError(404, "Product is not found");
    }

    return productId;
}

const create = async (user, request) => {
    const data = {
        product_id: request.product_id,
        product_name: request.product_name,
        stock: request.stock,
        price: request.price,
        created_by: user.user_id,
        updated_by: user.user_id
    }

    return prismaClient.m_product.create({
        data: data,
        select: {
            product_id: true,
            product_name: true,
            price: true,
            stock: true,
            created_by_user: {
                select: {
                    name: true
                },
            },
            updated_by_user: {
                select: {
                    name: true
                }
            },
            created_at: true,
            updated_at: true


        }
    })
}

const get = async (user, productId) => {

    await checkProductMustExists(user, productId);
    const product = await prismaClient.m_product.findFirst({
        where: {
            product_id: productId
        },
        select: {
            product_id: true,
            product_name: true,
            price: true,
            stock: true,
            created_by_user: {
                select: {
                    name: true
                },
            },
            updated_by_user: {
                select: {
                    name: true
                }
            },
            created_at: true,
            updated_at: true
        }
    });

    if (!product) {
        throw new ResponseError(404, "product is not found");
    }

    return product;
}

const update = async (user, request, product_id) => {
    await checkProductMustExists(user, product_id);


    const data = {
        product_id: request.product_id,
        product_name: request.product_name,
        stock: request.stock,
        price: request.price,
        updated_by: user.user_id
    }

    return prismaClient.m_product.update({
        where: {
            product_id: product_id
        },
        data: data,
        select: {
            product_id: true,
            product_name: true,
            price: true,
            stock: true,
            created_by_user: {
                select: {
                    name: true
                },
            },
            updated_by_user: {
                select: {
                    name: true
                }
            },
            created_at: true,
            updated_at: true
        }
    })

}

const remove = async (user, productId) => {
    await checkProductMustExists(user, productId);

    return prismaClient.m_product.update({
        where: {
            product_id: productId
        },
        data: {
            deleted_by: user.user_id,
            deleted_at: new Date()
        }
    })
}

const list = async (user, search, take, skip) => {
    const where = {
        deleted_by: null
    }

    if (search) {
        where.product_name = {
            contains: search
        }
    }

    const data = await prismaClient.m_product.findMany({
        where: where,
        take: take,
        skip: skip,
        select: {
            product_id: true,
            product_name: true,
            price: true,
            stock: true,
            created_by_user: {
                select: {
                    name: true
                },
            },
            updated_by_user: {
                select: {
                    name: true
                }
            },
            created_at: true,
            updated_at: true
        }
    });
    const total = await prismaClient.m_product.count({
        where: where
    });


    return {
        data,
        paging: {
            total: total,
            take: take,
            skip: skip
        }

    }

}

export default {
    create,
    get,
    update,
    remove,
    list
}
