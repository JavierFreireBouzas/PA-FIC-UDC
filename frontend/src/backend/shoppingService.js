import {appFetch, config} from "./appFetch";

export const comprar = (sesionId, numLocalidades, tarjetaBancaria, onSuccess, onErrors) =>
    appFetch(`/compras`, config('POST', {sesionId, numLocalidades, tarjetaBancaria}), onSuccess, onErrors);

export const encontrarCompras = ({page}, onSuccess) =>
    appFetch(`/compras?page=${page}`, config('GET'), onSuccess);

export const deliverTickets = (purchaseId, creditCard, onSuccess, onErrors) => {

    let path = `/compras/${purchaseId}/entregar-entradas`;

    appFetch(path, config('POST', {tarjetaBancaria: creditCard}), onSuccess, onErrors);
}

