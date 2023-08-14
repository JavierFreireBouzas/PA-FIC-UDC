import * as actionTypes from './actionTypes';
import {combineReducers} from "redux";

const initialState = {
    cartelera: null,
    carteleraDate: '',
    movie: null,
    sesion: null
};

const cartelera = (state  = initialState.cartelera, action) => {

    switch (action.type) {

        case actionTypes.GET_CARTELERA_COMPLETED:
            return action.cartelera;

        case actionTypes.CLEAR_CARTELERA:
            return initialState.cartelera;

        default:
            return state;

    }

}

const carteleraDate = (state  = initialState.carteleraDate, action) => {

    switch (action.type) {

        case actionTypes.GET_CARTELERA_COMPLETED:
            return action.date;

        default:
            return state;

    }
}

const movie = (state = initialState.movie, action) => {
    switch (action.type) {
        case actionTypes.MOVIE_BY_ID_COMPLETED:
            return action.movie;

        case actionTypes.MOVIE_BY_ID_CLEAR:
            return initialState.movie;

        default:
            return state;
    }
}


const sesion = (state = initialState.sesion, action) => {
    switch(action.type){
        case actionTypes.SESSION_BY_ID_COMPLETED:
            return action.sesion;

        case actionTypes.SESSION_BY_ID_CLEAR:
            return initialState.sesion;

        default:
            return state;
    }
}

const reducer = combineReducers({
    cartelera,
    carteleraDate,
    movie,
    sesion
});

export default reducer;