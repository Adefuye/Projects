import Navbar from './components/Navbar'
import ProjectCards from './components/ProjectCards'

function App() {


  return (
    <div className="pageContainer">
      <Navbar />
      
      <div className="wrapper">
        <ProjectCards />
      </div>
    </div>
  );
}

export default App;
