import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';

const SessionLink = ({id, hour}) => {

    return (
        <Link to={`/cartelera/sesiones/${id}`}>
            {hour}
        </Link>
    );

}

SessionLink.propTypes = {
    id: PropTypes.number.isRequired,
    hour: PropTypes.string.isRequired,
};

export default SessionLink;