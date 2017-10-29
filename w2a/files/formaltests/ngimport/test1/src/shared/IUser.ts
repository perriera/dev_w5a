/**
 * Created by perry on 2017-07-25.
 */

import { Document } from 'mongoose';

export interface IUser {
    username: string;
    email: string;
    password: string;
}

export interface IUserDocument extends Document, IUser {
}

