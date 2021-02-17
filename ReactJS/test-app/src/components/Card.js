const Card = ({card}) =>{
    return(
        <div className="card">
            <h3>{card.title}</h3>
            <p>{card.description}</p>
        </div>
    )
}

export default Card; 