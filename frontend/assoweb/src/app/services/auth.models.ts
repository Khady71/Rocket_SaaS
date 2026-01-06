export interface LoginRequest{
  email: string;
  password: string;
}

export interface RegisterRequest{
  email: string;
  firstName: string;
  lastName: string;
  password: string;
  phoneNumber: string;
  associationName: string;
  city: string;
}

export interface AuthResponse{
  token: string;
  refreshToken: string;
  user: User;
}

export interface User{
  id: string;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  phoneNumber: string;
  role: UserRole;
  associationName: Association;
  createdAt: Date;
}

export interface Association{
  id: string;
  name: string;
  city: string;
  logo: string;
  description: string;
  createdAt: Date;
}

export enum UserRole{
  ADMIN = 'ADMIN',
  MEMBER = 'MEMBER',
  SUPERADMIN = 'SUPERADMIN',
}
