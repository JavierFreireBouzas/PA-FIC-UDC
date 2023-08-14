import React, {useEffect} from "react";
import './styles/MovieDetail.css'
import * as selectors from "../selectors";
import {useParams} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import * as actions from '../actions';
import {BackLink} from "../../common";

export const MovieDetail = () => {
    const {id} = useParams();
    const movie = useSelector(selectors.getMovie);
    const dispatch = useDispatch();

    useEffect( () => {

        const movieId = Number(id);

        if(!Number.isNaN(id)){
            dispatch(actions.findMoviebyId(movieId))
        }

        return () => dispatch(actions.movieByIdClear);
        },[id,dispatch]

    )

    if(!movie){
        return null;
    }

    return(
        <div className = "movies-container">
            <BackLink/>
            <div className = "movies">
                <div className = "movies-header">
                    <h1>{movie.titulo}</h1>
                    <hr></hr>
                    <div className = "descripcion">
                        <p>{movie.descripcion}</p>
                        <p>Duración : {movie.duration} min</p>
                    </div>
                </div>
            </div>
        </div>
    );
}