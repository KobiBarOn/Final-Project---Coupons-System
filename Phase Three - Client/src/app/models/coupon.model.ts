import { Category } from './category.enum';
import { Company } from './company.model';
import { Customer } from './customer.model';

export class Coupon {
  constructor(
    public id?: number,
    public category?: Category,
    public title?: string,
    public description?: string,
    public startDate?: Date,
    public endDate?: Date,
    public amount?: number,
    public price?: number,
    public image?: string,
    public company?: Company,
    public customers?: Customer[]
  ) {}
}
