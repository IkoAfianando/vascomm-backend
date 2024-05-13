import express from "express";
import healthController from "../controller/health-controller.js";

const publicRouter = new express.Router();
publicRouter.get('/ping', healthController.ping);

export {
    publicRouter
}
