import {useState} from 'react'
import Navbar from './components/Navbar'
import CollectionCard from './components/CollectionCard'
import InputBar from './components/InputBar'
import CollectionsDiv from './components/CollectionsDiv'
//import Footer from './components/Footer'

const App = () => {
  const [cards, setcards] = useState([
    {
        id: 1,
        title: 'card #1',
        description: 'the first card',
    },
    {
        id: 2,
        title: 'card #2',
        description: 'the second card',
    },
  ])

  return (
    <div className="pageContainer">
      <Navbar /> 

      <div className="wrapper">
        <InputBar />

        <CollectionCard cards={cards} />
      </div>

    </div>
  );
}

export default App;
