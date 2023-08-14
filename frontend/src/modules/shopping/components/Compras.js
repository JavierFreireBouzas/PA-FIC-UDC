import './styles/Compras.css';
import {FormattedMessage, FormattedDate, FormattedTime} from 'react-intl';
import PropTypes from "prop-types";

const Compras = ({compras}) => {

    return (
        <div className="compras-container">
            {compras.map((compra, idx) =>
                <div key={idx} className="compras-item">
                    <div className="compras-item-title">
                        <h3><FormattedMessage id="project.shopping.FindOrdersResult.compraId"/>: {compra.idCompra}</h3>
                    </div>
                    <div className="compras-item-content">
                        <h6><FormattedMessage id="project.shopping.FindOrdersResult.fechaCompra"/>: <FormattedDate
                            value={new Date(compra.fechaCompra)}/> <FormattedTime value={new Date(compra.fechaCompra)}/></h6>
                        <h6 id="compraTituloPeli"> <FormattedMessage
                            id="project.shopping.FindOrdersResult.tituloPeli"/>: {compra.tituloPelicula}</h6>
                        <h6><FormattedMessage
                            id="project.shopping.FindOrdersResult.numLocalidades"/>: {compra.numLocalidades}</h6>
                        <h6><FormattedMessage
                            id="project.shopping.FindOrdersResult.totalPrice"/>: {compra.precioTotal} €</h6>
                        <h6><FormattedMessage id="project.shopping.FindOrdersResult.fechaSesion"/>: <FormattedDate
                            value={new Date(compra.fechaSesion)}/> <FormattedTime value={new Date(compra.fechaSesion)}/></h6>
                        <h6><FormattedMessage
                            id="project.shopping.FindOrdersResult.tituloEstadoEntrega"/>: {compra.entregadas &&
                            <FormattedMessage
                                id="project.shopping.FindOrdersResult.Entregadas"/>}{(!compra.entregadas) &&
                            <FormattedMessage id="project.shopping.FindOrdersResult.noEntregadas"/>}</h6>
                    </div>
                </div>
            )}
        </div>

    );
}


Compras.propTypes = {
    orders: PropTypes.array.isRequired
};

export default Compras;