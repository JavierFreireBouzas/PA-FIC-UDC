const getModuleState = state => state.shopping;

export const getUltimaCompraIdId = state =>
    getModuleState(state).ultimaCompraId;

export const getDeliveredPurchaseId = state =>
    getModuleState(state).deliveredPurchaseId;

export const getCompraSearch = state =>
    getModuleState(state).compraSearch;
