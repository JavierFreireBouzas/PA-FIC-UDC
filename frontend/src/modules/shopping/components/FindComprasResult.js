import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import './styles/FindComprasResult.css';

import * as actions from '../actions';
import * as selectors from '../selectors';
import {Pager} from '../../common';
import Compras from './Compras';

const FindComprasResult = () => {

    const compraSearch = useSelector(selectors.getCompraSearch);
    const dispatch = useDispatch();

    if (!compraSearch) {
        return null;
    }

    if (compraSearch.result.items.length === 0) {
        return (
            <div className="alert alert-info" role="alert">
                <FormattedMessage id='project.shopping.FindOrdersResult.sinCompras'/>
            </div>
        );
    }

    return (

        <div>
            <div className="comprastitle">
                <h1><FormattedMessage id="project.shopping.FindOrdersResult.Compras.title"/></h1>
            </div>
            <Compras compras={compraSearch.result.items}/>
            <Pager
                back={{
                    enabled: compraSearch.criteria.page >= 1,
                    onClick: () => dispatch(actions.previousFindComprasResultPage(compraSearch.criteria))}}
                next={{
                    enabled: compraSearch.result.existMoreItems,
                    onClick: () => dispatch(actions.nextFindComprasResultPage(compraSearch.criteria))}}/>
        </div>

    );

}

export default FindComprasResult;