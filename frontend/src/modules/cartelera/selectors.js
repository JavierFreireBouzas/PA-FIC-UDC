const getModuleState = state => state.cartelera;

export const getSession = state => getModuleState(state).sesion;
export const getMovie = state => getModuleState(state).movie;

export const getCartelera = state =>
    getModuleState(state).cartelera;

export const getCarteleraDate = state =>
    getModuleState(state).carteleraDate;