import {useState} from 'react'
import Navbar from './components/Navbar'
import CollectionCard from './components/CollectionCard'
import InputBar from './components/InputBar'
//import Footer from './components/Footer'

const App = () => {
  const [cards, setCards] = useState([
    {
        id: 1,
        title: 'Ultimate Charizard',
        description: 'Pokemon Base Set, PSA 10 GEM MINT',
    },
    {
        id: 2,
        title: 'Nike Air Force 1 Love Letter',
        description: 'Mens size 9 Limited Love Letter Air Force 1',
    },
  ])

  //add a card
  const addCard = (card) => {
    const id = Math.floor(Math.random() * 10000) + 1;
    const newCard = {id, ...card}
    setCards([...cards, newCard])
  }

  //remove a card
  const removeCard = (id) =>{
    setCards(cards.filter((cards) => cards.id !==id))
  }

  return (
    <div className="pageContainer">
      

      <div className="wrapper">
        <h1>My Collection</h1>
        <InputBar onAdd={addCard}/>

        {cards.length > 0 ? 
          <CollectionCard cards={cards}
          onRemove={removeCard} /> : 'Add Items to your collection'
        }
      </div>

    </div>
  );
}

export default App;
