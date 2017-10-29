/**
 * Created by perry on 2017-07-25.
 */

import { Document } from 'mongoose';
import { IUserDocument } from './IUser';

export interface IArticle {
    user: IUserDocument;
    title: string;
    summary: string;
    text: string;
    posted: Date;
    published: Boolean;
}

export interface IArticleDocument extends Document, IArticle {
}
