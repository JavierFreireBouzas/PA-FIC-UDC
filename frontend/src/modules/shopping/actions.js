import * as actionTypes from './actionTypes';
import backend from '../../backend';

const comprarCompleted = (compraId) => ({
        type: actionTypes.COMPRAR_COMPLETED,
        compraId
});

export const comprar = (sesionId, numLocalidades, tarjetaBancaria, onSuccess, onErrors) => dispatch =>
    backend.shoppingService.comprar(sesionId, numLocalidades, tarjetaBancaria, id => {
            dispatch(comprarCompleted(id));
            onSuccess();
        },
        onErrors);

const deliverTicketsCompleted = (deliveredPurchaseId) => ({
    type: actionTypes.DELIVER_TICKETS_COMPLETED,
    deliveredPurchaseId
});

export const deliverTickets = (purchaseId, creditCard, onSuccess, onErrors) => dispatch =>
    backend.shoppingService.deliverTickets(purchaseId, creditCard, id => {
            dispatch(deliverTicketsCompleted(id));
            onSuccess();
        },
        onErrors);

const findComprasCompleted = compraSearch => ({
    type: actionTypes.FIND_COMPRAS_COMPLETED,
    compraSearch
});

const clearCompraSearch = () => ({
    type: actionTypes.CLEAR_COMPRA_SEARCH
});

export const findCompras = criteria => dispatch => {

    dispatch(clearCompraSearch());
    backend.shoppingService.encontrarCompras(criteria,
        result => dispatch(findComprasCompleted({criteria, result})));

}

export const previousFindComprasResultPage = criteria =>
    findCompras({page: criteria.page-1});

export const nextFindComprasResultPage = criteria =>
    findCompras({page: criteria.page+1});

