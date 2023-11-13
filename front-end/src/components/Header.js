import { Link, useLocation } from 'react-router-dom';
import '../styles/Header.css';

export default function Header() {
    const location = useLocation();

    function HeaderLink({ to, children }) {
        const location = useLocation();
        const isActive = (pathname) => location.pathname.startsWith(pathname);

        return (
            <Link to={to} className={isActive(to) ? 'active-menu' : ''}>
                {children}
            </Link>
        );
    }

    return (
        <div className="header">
            <h1>
                <Link to='/now'>
                    <img src="https://d3udu241ivsax2.cloudfront.net/v3/images/common/millie-logo.3884f0c52e69f1f7322c2fd4778f4830.png" alt="밀리의 서재" class="home-logo"/>
                </Link>
            </h1>
            <nav>
                <ul>
                    <li><HeaderLink to='/today' >투데이</HeaderLink></li>
                    <li><HeaderLink to='/feed'>피드</HeaderLink></li>
                    <li><HeaderLink to='/search'>검색</HeaderLink></li>
                    <li><HeaderLink to='/myshelf'>내서재</HeaderLink></li>
                    <li><HeaderLink to='/management'>관리</HeaderLink></li>
                </ul>
            </nav>
            <ul className='right'>
                <li><Link to='#'>로그아웃</Link></li>
            </ul>
        </div>
    );
}