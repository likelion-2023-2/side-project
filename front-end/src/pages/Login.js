import '../styles/Login.css';
import LoginImage from '../assets/images/intro.jpg';
import { Link } from 'react-router-dom';

function Login() {

    return (
        <div className='login-wrap'>
            <div className='background-img'>
                <img src={LoginImage} alt='로그인 이미지' />
            </div>
            <div className='login'>
                <h3 className="title">독서와 무제한 친해지리</h3>
                <p className="sub-title">15만 권 속에서 인생책을 찾아보세요</p>
                <Link to="/main">메인 페이지로 이동 (임시로 이거 클릭)</Link>
            </div>
        </div>
    );
}

export default Login;