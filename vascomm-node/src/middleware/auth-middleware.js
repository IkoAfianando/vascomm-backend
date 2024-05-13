import {prismaClient} from "../application/database.js";
import jwt from "jsonwebtoken";

export const authMiddleware = async (req, res, next) => {
    const token = req.get('X-API-TOKEN');
    if (!token) {
        res.status(401).json({
            errors: "Unauthorized"
        }).end();
    } else {
        const user = await prismaClient.m_user.findFirst({
            where: {
                access_token: token
            }
        });

        if (!user) {
            res.status(401).json({
                errors: "Unauthorized"
            }).end();
        } else {
            req.user = user;
            next();
        }
    }
}
