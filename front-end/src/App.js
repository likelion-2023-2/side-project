import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Login from './pages/Login';
import Now from './pages/Now';
import Feed from './pages/Feed';
import Search from './pages/Search';
import Myshelf from './pages/Myshelf';
import Management from './pages/Management';
import Story from './pages/Story';
import Following from './pages/Following';
import Post from './pages/Post';
import Highlight from './pages/Highlight';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route exact path='/' element={<Login/>}/>
          <Route exact path='/today' element={<Now/>}/>
          <Route exact path='/today/now' element={<Now/>}/>
          <Route exact path='/feed' element={<Feed/>}/>
          <Route exact path='/search' element={<Search/>}/>
          <Route exact path='/myshelf' element={<Myshelf/>}/>
          <Route exact path='/management' element={<Management/>}/>
          <Route exact path='/today/스토리' element={<Story/>}/>
          <Route exact path='/feed/추천' element={<Feed/>}/>
          <Route exact path='/feed/팔로잉' element={<Following/>}/>
          <Route exact path='/feed/포스트' element={<Post/>}/>
          <Route exact path='/feed/하이라이트' element={<Highlight/>}/>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;