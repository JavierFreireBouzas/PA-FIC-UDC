import * as actionTypes from './actionTypes';
import backend from '../../backend';

const getCarteleraCompleted = (cartelera, date) => ({
    type: actionTypes.GET_CARTELERA_COMPLETED,
    cartelera,
    date
});

export const getCartelera = date => dispatch => {

    dispatch(clearCartelera());
    // dispatch(setCarteleraDate(date));
    backend.carteleraService.getCartelera(date,
        result => dispatch(getCarteleraCompleted(result, date)));
}

const clearCartelera = () => ({
    type: actionTypes.CLEAR_CARTELERA
});

export const findMoviebyIdCompleted = movie => ({
    type : actionTypes.MOVIE_BY_ID_COMPLETED,
    movie
})

export const movieByIdClear = () => ({
    type: actionTypes.MOVIE_BY_ID_CLEAR
})

export const findMoviebyId = id => dispatch => {
    backend.carteleraService.findMoviebyId(id, movie => dispatch(findMoviebyIdCompleted(movie)))
}


export const findSessionbyIdCompleted = sesion =>({
    type: actionTypes.SESSION_BY_ID_COMPLETED,
    sesion
})

export const sessionByIdClear = () => ({
    type: actionTypes.SESSION_BY_ID_CLEAR
})

export const findSessionbyId = (id, onErrors) => dispatch => {
    backend.carteleraService.findSessionbyId(id, sesion => dispatch(findSessionbyIdCompleted(sesion)), onErrors)
}