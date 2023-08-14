import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import * as selectors from '../selectors';
import PropTypes, {bool} from "prop-types";
import Errors from "../../common/components/Errors";

const DeliverTicketsCompleted = ({deliverCompleted, onClose}) => {

    const purchaseId = useSelector(selectors.getDeliveredPurchaseId);

    if (!purchaseId) {
        return null;
    }

    return (
        <div>
            {
                deliverCompleted &&
                <div id="deliverTicketsSuccess" className="alert alert-success" role="alert">
                    <FormattedMessage id="project.shopping.deliverTicketsCompleted"/>&nbsp;{purchaseId}
                    <button type="button" className="close" data-dismiss="alert" aria-label="Close"
                            onClick={() => onClose()}>
                        <span aria-hidden="true">&times;</span>
                    </button>

                </div>
            }
        </div>
    );
}

Errors.propTypes = {
    deliverCompleted: bool,
    onClose: PropTypes.func.isRequired
};

export default DeliverTicketsCompleted;