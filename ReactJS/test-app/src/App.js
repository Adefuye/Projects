import Navbar from './components/Navbar'
import CollectionCard from './components/CollectionCard'
import InputBar from './components/InputBar'
//import Footer from './components/Footer'

function App() {


  return (
    <div className="pageContainer">
      <Navbar />

      <div className="wrapper">
        <InputBar />
        
        <CollectionCard />
        <CollectionCard />
        <CollectionCard />
      </div>

    </div>
  );
}

export default App;
