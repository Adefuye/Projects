import Card from "./Card"

const CollectionCard = ({ cards, onRemove }) => {

    return(
        <>
            {cards.map((card) => (
                <Card key={card.id} card={card} onRemove={onRemove} />
            ))}
        </>
    )
}

export default CollectionCard