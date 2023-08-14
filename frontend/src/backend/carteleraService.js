import {appFetch, config} from "./appFetch";

export const getCartelera = (date, onSuccess, onErrors) => {

    let path = `/cartelera/catalog?date=${date}`;

    appFetch(path, config('GET'), onSuccess, onErrors);
}

export const findMoviebyId = (id, onSuccess, onErrors) =>
    appFetch(`/cartelera/peliculas/${id}`, config('GET'), onSuccess, onErrors);

export const findSessionbyId = (id, onSuccess, onErrors) =>
    appFetch(`/cartelera/sesiones/${id}`, config('GET'), onSuccess, onErrors)