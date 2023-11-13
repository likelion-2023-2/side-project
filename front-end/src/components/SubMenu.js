import { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import '../styles/SubMenu.css';

export default function SubMenu() {
    const location = useLocation();
    const [menuItems, setMenu] = useState([]);

    useEffect(() => {
        setMenu(['포스트', '한 줄 리뷰', '하이라이트', '인생책']);
    }, [location.pathname]);

    return (
        <div className="sub-menu">
            <ul>
                {menuItems.map((menuItem) => (
                    <li key={menuItem}>
                        <Link to={`/feed/${menuItem.toLowerCase()}`}>
                            <button>{menuItem}</button>
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}