import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Login from './pages/Login';
import Main from './pages/Main';
import Feed from './pages/Feed';
import Search from './pages/Search';
import Myshelf from './pages/Myshelf';
import Management from './pages/Management';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route exact path='/' element={<Login/>}/>
          <Route exact path='/main' element={<Main/>}/>
          <Route exact path='/feed' element={<Feed/>}/>
          <Route exact path='/search' element={<Search/>}/>
          <Route exact path='/myshelf' element={<Myshelf/>}/>
          <Route exact path='/management' element={<Management/>}/>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;