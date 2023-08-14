import PropTypes from 'prop-types';
import './MovieLink.css'

import {Link} from 'react-router-dom';

const MovieLink = ({id, nombrePelicula}) => {
    return(
        <div className = "tituloPeli">
            <Link to={`/cartelera/peliculas/${id}`}>
                {nombrePelicula}
            </Link>
        </div>
    )
}

MovieLink.propTypes = {
    id: PropTypes.number.isRequired,
    nombrePelicula: PropTypes.string.isRequired
};

export default MovieLink;