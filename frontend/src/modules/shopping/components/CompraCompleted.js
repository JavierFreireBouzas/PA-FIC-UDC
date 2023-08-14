import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import * as selectors from '../selectors';
import {Link} from "react-router-dom";

export const CompraCompleted = () => {

    const compraId = useSelector(selectors.getUltimaCompraIdId);

    if (!compraId) {
        return null;
    }

    return (
        <div>
            <div className="alert alert-success" id="resultadoCompra" role="alert">
                <FormattedMessage id="project.shopping.CompraCompleted.compraGenerada"/>:
                &nbsp;
                {compraId}


            </div>
            <div>
                <Link to={`/`}>
                    <FormattedMessage id="project.shopping.CompraCompleted.regresarCartelera"/>
                </Link>
            </div>
        </div>




    );

}

export default CompraCompleted;
