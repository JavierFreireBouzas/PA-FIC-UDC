import {useState} from 'react';
import {useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useNavigate} from 'react-router-dom';

import {Errors} from '../../common';
import * as actions from '../actions';

export const FormCompra = ({sesionId}) => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [numEntradas, setNumEntradas] = useState('');
    const [numTarjeta, setNumTarjeta] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    const handleSubmit = event => {
        event.preventDefault();
        if (form.checkValidity()) {
            dispatch(actions.comprar(sesionId,
                Number(numEntradas), numTarjeta.trim(),
                () => navigate('/shopping/comprar-completed'),
                errors => setBackendErrors(errors)));
        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }
    }

    return (

        <div>
            <Errors errors={backendErrors}
                    onClose={() => setBackendErrors(null)}/>
            <div className="card bg-light border-dark" id="formCompra">
                <h5 className="card-header">
                    <FormattedMessage id="project.shopping.FormCompra.title"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                          className="needs-validation" noValidate
                          onSubmit={(e) => handleSubmit(e)}>
                          <div className="form-group row">
                            <label htmlFor="numEntradas" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.numLocalidades"/>
                            </label>
                            <div className="col-md-2">
                                <input type="number" id="quantity" className="form-control"
                                       value={numEntradas}
                                       onChange={e => setNumEntradas(Number(e.target.value))}
                                       autoFocus
                                       min="1"
                                       max="10" />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.incorrectQuantity'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="numTarjeta" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.numTarjeta"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="numTarjeta" className="form-control"
                                       value={numTarjeta}
                                       onChange={e => setNumTarjeta(e.target.value)}
                                       required
                                       minLength="16"
                                       maxLength="16"/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-1">
                                <button type="submit" id="submitCompra" className="btn btn-primary">
                                    <FormattedMessage id="project.global.buttons.comprar"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default FormCompra;
