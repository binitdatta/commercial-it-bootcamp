document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.dropdown-submenu > .dropdown-item').forEach(item => {
        item.addEventListener('click', event => {
            if (window.innerWidth < 992) {
                event.preventDefault();
                event.stopPropagation();
                const menu = item.nextElementSibling;
                if (menu) menu.classList.toggle('show');
            }
        });
    });
});
