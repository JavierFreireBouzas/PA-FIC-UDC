import './styles/Movies.css';
import {useSelector} from 'react-redux';
import * as selectors from "../selectors";
import {format} from 'date-fns'
import {useNavigate} from "react-router-dom";
import SessionLink from "../../common/components/SessionLink";
import {FormattedMessage} from "react-intl";

export const Movies = () => {
    const cartelera = useSelector(selectors.getCartelera);
    const navigate = useNavigate();

    const toHour = (timestamp) => {
        return format(new Date(timestamp), 'HH:mm');
    }

    const navigateToMovieDetail = (id) => {
        const path = `/cartelera/peliculas/${id}`;
        navigate(path);
    }

    return (
        <div className="cartelera-container">
            {cartelera && cartelera.map((movie, idx) =>
                <div key={idx} className="cartelera-item">
                    <div className="cartelera-item-title"
                         onClick={() => navigateToMovieDetail(movie.peliculaId)}>
                        <h3>{movie.titulo}</h3>
                    </div>
                    <div className="cartelera-item-content">
                        <h6>
                            <FormattedMessage id="project.app.Home.Billboard.Movies.Sessions.title"/>
                        </h6>
                        <ul>
                            {movie.sesions && movie.sesions.map((session, idx) =>
                                <li key={idx}>
                                    <SessionLink hour={toHour(session.date)} id={session.id}/>
                                </li>
                            )}
                        </ul>
                    </div>
                </div>
            )}
        </div>
    );
}