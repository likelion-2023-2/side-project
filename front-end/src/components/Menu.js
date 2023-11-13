import { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import '../styles/Menu.css';

export default function Menu() {
    const location = useLocation();
    const [menuItems, setMenu] = useState([]);

    useEffect(() => {
        if (location.pathname.includes('/today')) {
            setMenu(['NOW', '스토리', '밀리로드']);
        } else if (location.pathname.includes('/feed')) {
            setMenu(['추천', '팔로잉']);
        }  else {
            setMenu([]);
        }
    }, [location.pathname]);

    return (
        <div className="menu">
            <ul>
                {menuItems.map((menuItem) => (
                    <li key={menuItem}>
                        <Link
                            to={
                                menuItem === '추천' || menuItem === '팔로잉' 
                                    ? `/feed/${menuItem.toLowerCase()}`
                                    : location.pathname.includes('/today')
                                        ? `/today/${menuItem.toLowerCase()}`
                                        : `/${menuItem.toLowerCase()}`
                            }
                            className={
                                location.pathname === 
                                    (
                                        menuItem === '추천' || menuItem === '팔로잉'
                                            ? `/feed/${menuItem.toLowerCase()}`
                                            : (location.pathname.includes('/today')
                                                ? `/today/${menuItem.toLowerCase()}`
                                                : `/${menuItem.toLowerCase()}`)
                                    ) 
                                    ? 'active' : ''
                            }
                        >
                            {menuItem}
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}