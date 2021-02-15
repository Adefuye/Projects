import Navbar from './components/Navbar'
import ProjectCards from './components/ProjectCards'
//import Footer from './components/Footer'

function App() {


  return (
    <div className="pageContainer">
      <Navbar />

      <div className="wrapper">
        <ProjectCards />
        <ProjectCards />
        <ProjectCards />
      </div>

    </div>
  );
}

export default App;
