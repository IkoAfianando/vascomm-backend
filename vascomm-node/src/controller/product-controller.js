import productService from "../service/product-service.js";

const create = async (req, res, next) => {
    try {
        const user = req.user;
        const request = req.body;

        const result = await productService.create(user,  request);

        res.status(200).json({
            code: 201,
            message: "Created",
            data: result
        });
    } catch (e) {
        next(e);
    }
}

const get = async (req, res, next) => {
    try {
        const user = req.user;
        const productId = req.params.productId;

        const result = await productService.get(user, productId);

        res.status(200).json({
            code: 200,
            message: "OK",
            data: result
        });
    } catch (e) {
        next(e);
    }
}

const update = async (req, res, next) => {
    try {
        const user = req.user;
        const request = req.body;
        const productId = req.params.productId;

        const result = await productService.update(user, request, productId);

        res.status(200).json({
            code: 200,
            message: "OK",
            data: result
        });
    } catch (e) {
        next(e);
    }
}

const remove = async (req, res, next) => {
    try {
        const user = req.user;
        const productId = req.params.productId;

        await productService.remove(user, productId);

        res.status(200).json({
            code: 200,
            message: "OK",
            data: "Product has been deleted"
        });
    } catch (e) {
        next(e);
    }
}

const list = async (req, res, next) => {
    try {
        const user = req.user;
        const search = req.query.search;
        const take = req.query.take ? parseInt(req.query.take) : 10;
        const skip = req.query.skip ? parseInt(req.query.skip) : 0;

        const result = await productService.list(user, search, take, skip);
        res.status(200).json({
            code: 200,
            message: "OK",
            data: result["data"],
            paging: result["paging"]

        })
    } catch (e) {
        next(e);
    }
}

export default {
    create,
    get,
    update,
    remove,
    list
}
