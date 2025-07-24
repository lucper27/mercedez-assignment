import { Component, inject } from '@angular/core';
import { Menubar } from 'primeng/menubar';
import { MenuItem } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [Menubar],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

  private readonly router = inject(Router);

  items: MenuItem[] | undefined;

  ngOnInit() {
    this.loadItems();
  }

  toggleDarkMode() {
    const element = document.querySelector('html');
    element!.classList.toggle('my-app-dark');
  }

  private loadItems(): void {
        this.items = [
            {
                label: 'People',
                icon: 'pi pi-users',
                command: () => {
                    this.router.navigate(['/people']);
                }
            },
            {
                label: 'Planets',
                icon: 'pi pi-globe',
                command: () => {
                    this.router.navigate(['/planets']);
                }
            },
            {
                label: 'Dark Mode',
                icon: 'pi pi-moon',
                command: () => {
                    this.toggleDarkMode();
                }
            }
        ]
  }

}
