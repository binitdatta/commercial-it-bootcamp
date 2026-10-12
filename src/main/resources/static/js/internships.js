// Works with Bootstrap 5; submenu buttons support mouse, keyboard and touch.
document.addEventListener('DOMContentLoaded',()=>{
 document.querySelectorAll('.internship-dropdown .submenu-toggle').forEach(button=>{
   button.addEventListener('click',event=>{event.preventDefault();event.stopPropagation();const parent=button.closest('.dropdown-submenu');const opened=parent.classList.toggle('open');button.setAttribute('aria-expanded',String(opened));});
 });
 document.querySelectorAll('.internship-dropdown').forEach(menu=>menu.addEventListener('hide.bs.dropdown',()=>{menu.querySelectorAll('.dropdown-submenu.open').forEach(item=>item.classList.remove('open'));menu.querySelectorAll('.submenu-toggle').forEach(b=>b.setAttribute('aria-expanded','false'));}));
});
