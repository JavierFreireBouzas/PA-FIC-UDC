import './styles/DeliverTickets.css'
import {FormattedMessage} from "react-intl";
import {useState} from "react";
import {useDispatch} from "react-redux";
import * as actions from "../actions";
import {Errors} from "../../common";
import DeliverTicketsCompleted from "./DeliverTicketsCompleted";

export const DeliverTickets = () => {

    const dispatch = useDispatch();
    const [purchaseId, setPurchaseId] = useState('');
    const [creditCard, setCreditCard] = useState('');
    const [deliverCompleted, setDeliverCompleted] = useState(false);
    const [backendErrors, setBackendErrors] = useState(null);
    let form;
    const handleSubmit = event => {
        event.preventDefault();
        if (form.checkValidity()) {
            dispatch(actions.deliverTickets(Number(purchaseId.trim()), creditCard.trim(),
                () => setDeliverCompleted(true),
                errors => setBackendErrors(errors)));
        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }
    }

    return (
        <div>
            <DeliverTicketsCompleted deliverCompleted={deliverCompleted}
                                     onClose={() => setDeliverCompleted(false)}/>
            <Errors errors={backendErrors}
                    onClose={() => setBackendErrors(null)}/>
            <div className="card bg-light border-dark">
                <h5 className="card-header">
                    <FormattedMessage id="project.shopping.DeliverTickets.title"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                          className="needs-validation" noValidate
                          onSubmit={(e) => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="purchaseId" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.purchaseId"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="purchaseId" className="form-control"
                                       value={purchaseId}
                                       onChange={(e) => setPurchaseId(e.target.value)}
                                       autoFocus
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="project.global.fields.tarjetaBancaria" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.numTarjeta"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="numTarjeta" className="form-control"
                                       value={creditCard}
                                       onChange={e => setCreditCard(e.target.value)}
                                       required
                                       minLength="16"
                                       maxLength="16"/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.invalidCreditCard'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-1">
                                <button id="deliverTicketsSubmit" type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.global.buttons.deliverTickets"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}