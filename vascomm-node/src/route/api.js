import express from "express";
import {authMiddleware} from "../middleware/auth-middleware.js";
import productController from "../controller/product-controller.js";

const userRouter = new express.Router();
userRouter.use(authMiddleware);
userRouter.get('/', (req, res) => {
    res.send('Hello World');
});
userRouter.post('/api/products', productController.create);
userRouter.put('/api/products/:productId', productController.update);
userRouter.get('/api/products/:productId', productController.get);
userRouter.delete('/api/products/:productId', productController.remove);
userRouter.get('/api/products', productController.list);

export {
    userRouter
}
