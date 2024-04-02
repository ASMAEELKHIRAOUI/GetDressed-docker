import { Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { HomeComponent } from './components/client/home/home.component';
import { LayoutComponent } from './components/auth/layout/layout.component';
import { NavbarComponent } from './components/client/navbar/navbar.component';
import { SidebarComponent } from './components/admin/sidebar/sidebar.component';
import { ListProductComponent } from './components/admin/product/list-product/list-product.component';
import { AddProductComponent } from './components/admin/product/add-product/add-product.component';
import { EditProductComponent } from './components/admin/product/edit-product/edit-product.component';
import { ListOrderComponent } from './components/admin/order/list-order/list-order.component';
import { EditOrderComponent } from './components/admin/order/edit-order/edit-order.component';
import { OrderDetailsComponent } from './components/admin/order/order-details/order-details.component';
import { ProductDetailsComponent } from './components/client/product-details/product-details.component';
import { CartComponent } from './components/client/cart/cart.component';
import { OrderComponent } from './components/client/order/order.component';
import { ProfileComponent } from './components/client/profile/profile.component';
import { adminGuard } from './guards/admin/admin.guard';
import { authGuard } from './guards/auth/auth.guard';

export const routes: Routes = [
    {
        path: '',
        component: NavbarComponent,
        children: [
            { path: '', component: HomeComponent },
            { path: 'product/:id', component: ProductDetailsComponent },
            { path: 'cart', component: CartComponent, canActivate: [authGuard] },
            { path: 'checkout', component: OrderComponent, canActivate: [authGuard] },
            { path: 'profile', component: ProfileComponent, canActivate: [authGuard] }
        ]
    },
    {
        path: 'auth',
        component: LayoutComponent,
        children: [
            { path: 'login', component: LoginComponent },
            { path: 'register', component: RegisterComponent }
        ]
    },
    {
        path: 'dashboard',
        component: SidebarComponent,
        canActivate:[adminGuard],
        children: [
            { path: 'product', component: ListProductComponent },
            { path: 'product/add', component: AddProductComponent },
            { path: 'product/edit/:id', component: EditProductComponent },
            { path: 'order', component: ListOrderComponent },
            { path: 'order/edit/:id', component: EditOrderComponent },
            { path: 'order/details/:id', component: OrderDetailsComponent }
        ]
    },

];
