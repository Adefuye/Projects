import { FaTimes } from 'react-icons/fa'

const Card = ({card}) =>{
    return(
        <div className="card">
             <FaTimes className="xBtn"/>
            <h3>
                {card.title}
            </h3>
            <p>{card.description}</p>
        </div>
    )
}

export default Card; 