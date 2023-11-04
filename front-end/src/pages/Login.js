import '../styles/Login.css';
import LoginApple from '../assets/images/apple-icon.svg';
import LoginFacebook from '../assets/images/facebook-icon.svg';
import LoginGoogle from '../assets/images/google-icon.svg';
import LoginKakao from '../assets/images/kakao-icon.svg';
import LoginNaver from '../assets/images/naver-icon.svg';
import { Link } from 'react-router-dom';

const linkStyle = {
    color: '#8b8b8b',
    fontWeight: '700',
    fontSize: '14px',
    textAlign: 'center',
    display: 'block',
    margin: '40px auto 24px',
    textDecorationLine: 'underline',
};

function Login() {
    return (
        <div className='login-wrap'>
            <div className='background-img'></div>
            <div className='login'>
                <h3 className="title">독서와 무제한 친해지리</h3>
                <p className="sub-title">15만 권 속에서 인생책을 찾아보세요</p>
                <div className='input-area'>
                    <form>
                        <div className='input-box'>
                            <span className="input-label">휴대폰 번호</span>
                            <input autoComplete="off" className="input" type="number" placeholder="01012345678" pattern="\d*"></input>
                        </div>
                        <div className='input-box'>
                            <span className="input-label">비밀번호</span>
                            <input autoComplete="off" className="input" type="password" placeholder="비밀번호 입력"></input>
                        </div>
                        <button className='btn'>로그인</button>
                    </form>
                </div>
                <ul className='sub-menu'>
                    <li><a>회원가입</a></li>
                    <li><a>비밀번호 찾기</a></li>
                    <li><a>기업회원 로그인</a></li>
                </ul>
                <div className='or'><p>또는</p></div>
                <div className='sns-list'>
                    <button><img src={LoginKakao}/></button>
                    <button><img src={LoginNaver}/></button>
                    <button><img src={LoginFacebook}/></button>
                    <button><img src={LoginApple}/></button>
                    <button><img src={LoginGoogle}/></button>
                </div>
                <Link to="/main" style={linkStyle}>메인 페이지로 이동 (임시로 이거 클릭)</Link>
            </div>
        </div>
    );
}

export default Login;