import Card from "./Card"

const CollectionCard = ({ cards }) => {

    return(
        <>
            {cards.map((card) => (
                <Card key={card.id} card={card} />
            ))}
        </>
    )
}

export default CollectionCard