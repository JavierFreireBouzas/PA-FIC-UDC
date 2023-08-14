import {useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {useNavigate} from 'react-router-dom';

import * as actions from '../actions';

const FindCompras = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {

        dispatch(actions.findCompras({page: 0}));
        navigate('/shopping/find-compras-result');

    });

    return null;

}

export default FindCompras;