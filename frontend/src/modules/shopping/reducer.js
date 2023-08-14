import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    ultimaCompraId: null,
    deliveredPurchaseId: null,
    compraSearch: null
};

const ultimaCompraId = (state = initialState.ultimaCompraId, action) => {

    switch (action.type) {

        case actionTypes.COMPRAR_COMPLETED:
            return action.compraId;

        default:
            return state;
    }
}

const deliveredPurchaseId = (state  = initialState.deliveredPurchaseId, action) => {
    switch (action.type) {

        case actionTypes.DELIVER_TICKETS_COMPLETED:
            return action.deliveredPurchaseId;

        default:
            return state;

    }
}

const compraSearch = (state = initialState.compraSearch, action) => {

    switch (action.type) {

        case actionTypes.FIND_COMPRAS_COMPLETED:
            return action.compraSearch;

        case actionTypes.CLEAR_COMPRA_SEARCH:
            return initialState.compraSearch;

        default:
            return state;

    }

}

const reducer = combineReducers({
    ultimaCompraId,
    deliveredPurchaseId,
    compraSearch
});

export default reducer;