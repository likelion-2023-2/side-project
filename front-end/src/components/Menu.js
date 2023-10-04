import { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import '../styles/Menu.css';

export default function Menu() {
    const location = useLocation();
    const [menuItems, setMenu] = useState([]);

    useEffect(() => {
        if (location.pathname === '/main') {
            setMenu(['NOW', '스토리', '밀리로드']);
        } else if (location.pathname === '/feed') {
            setMenu(['추천', '팔로잉']);
        }
    }, [location.pathname]);

    return (
        <div className="menu">
            <ul>
                {menuItems.map((menuItem) => (
                    <li key={menuItem}>
                        <Link
                            to={`/${menuItem.toLowerCase()}`}
                            className={location.pathname === `/${menuItem.toLowerCase()}` ? 'active' : ''}
                        >
                            {menuItem}
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}