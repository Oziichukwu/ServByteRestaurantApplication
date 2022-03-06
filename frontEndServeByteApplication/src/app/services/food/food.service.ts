import { Injectable } from '@angular/core';
import { Food } from '../../shared/models/Food';
import { Tag } from '../../shared/models/Tag';


@Injectable({
  providedIn: 'root'
})
export class FoodService {

  constructor() { }

  getFoodById(id: number): Food{
    return this.getAll().find(food => food.id == id)!;
  }

  getAllTags(): Tag[]{

    return [
      { name: 'All', count: 14 },
      { name: 'Swallow', count: 4 },
      { name: 'Dinner', count: 2 },
      { name: 'Lunch', count: 3 },
      { name: 'SlowFood', count: 2 },
      { name: 'Grain', count: 1 },
      { name: 'Fry', count: 1 },
      { name: 'Soup', count: 1 },
    ];

  }

  getAllFoodsByTag(tag: string): Food[] {
    return tag == "All" ?
      this.getAll() :
      this.getAll().filter(food => food.tags?.includes(tag));
  }

  getAllFoodsBySearchTerm(searchTerm:string) :Food[]{
    return  this.getAll().filter(food =>
      food.name.toLowerCase().includes(searchTerm.toLowerCase()));
  }

  getAll():Food[]{

    return [

        {
          id: 1,
          name: 'amala',
          cookTime: '10-20',
          price: 1500,
          favorite: false,
          origins: ['Nigeria', 'yoruba'],
          stars: 4.5,
          imageUrl: '/assets/images/foods/amala.jpg',
          tags: ['Dinner', 'swallow', 'Lunch'],
        },
        {
          id: 2,
          name: 'egusi-soup with eba',
          cookTime: '20-30',
          price: 1500,
          favorite: false,
          origins: ['Nigeria'],
          stars: 4.5,
          imageUrl: '/assets/images/foods/nigeriafood3.jpg',
          tags: ['Dinner', 'Swallow', 'Lunch'],
        },
        {
          id: 3,
          name: 'egusi-soup with eba',
          cookTime: '20-30',
          price: 1500,
          favorite: false,
          origins: ['Nigeria'],
          stars: 4.5,
          imageUrl: '/assets/images/foods/nigeriafood4.jpg',
          tags: ['Dinner', 'Swallow', 'Lunch'],
        },
        {
          id: 4,
          name: 'egusi-soup with eba',
          cookTime: '20-30',
          price: 1500,
          favorite: false,
          origins: ['Nigeria'],
          stars: 4.5,
          imageUrl: '/assets/images/foods/nigerianfood1.jpg',
          tags: ['Dinner', 'Swallow', 'Lunch'],
        },
        {
          id: 5,
          name: 'egusi-soup with eba',
          cookTime: '20-30',
          price: 1500,
          favorite: false,
          origins: ['Nigeria'],
          stars: 4.5,
          imageUrl: '/assets/images/foods/nigerianfood2.jpg',
          tags: ['Dinner', 'Swallow', 'Lunch'],
        },
        {
          id: 6,
          name: 'draw-soup with eba',
          cookTime: '20-30',
          price: 1500,
          favorite: false,
          origins: ['Nigeria'],
          stars: 4.5,
          imageUrl: '/assets/images/foods/nigerianfood5.jpg',
          tags: ['Dinner', 'Swallow', 'Lunch'],
        },
        {
          id: 7,
          name: 'draw-soup with eba',
          cookTime: '20-30',
          price: 1500,
          favorite: false,
          origins: ['Nigeria'],
          stars: 4.5,
          imageUrl: '/assets/images/foods/nigerianfood6.jpg',
          tags: ['Dinner', 'Swallow', 'Lunch'],
        },
        {
          id: 8,
          name: 'draw-soup with eba',
          cookTime: '20-30',
          price: 1500,
          favorite: false,
          origins: ['Nigeria', 'igbo'],
          stars: 4.5,
          imageUrl: '/assets/images/foods/nigerianfood7.jpg',
          tags: ['Dinner', 'Swallow', 'Lunch'],
        },
        {
          id: 9,
          name: 'draw-soup with eba',
          cookTime: '20-30',
          price: 1500,
          favorite: false,
          origins: ['Nigeria', 'igbo'],
          stars: 4.5,
          imageUrl: '/assets/images/foods/nigerianfood9.jpg',
          tags: ['Dinner', 'Swallow', 'Lunch'],
        },
        {
          id: 10,
          name: 'plantain-with-beans',
          cookTime: '20-30',
          price: 1500,
          favorite: false,
          origins: ['Nigeria', 'igbo'],
          stars: 4.5,
          imageUrl: '/assets/images/foods/nigerianfood10.jpg',
          tags: ['Dinner', 'Plantain', 'Lunch'],
        },
        {
          id: 11,
          name: 'plantain-with-beans',
          cookTime: '20-30',
          price: 1500,
          favorite: false,
          origins: ['Nigeria', 'igbo'],
          stars: 4.5,
          imageUrl: '/assets/images/foods/nigerianfood11.jpeg',
          tags: ['Dinner', 'Plantain', 'Lunch'],
        },

      // '/assets/images/foods/nigerianfood12.jpg',
      // '/assets/images/foods/nigerianfood13.png',
      // '/assets/images/foods/nigerianfood15.jpg',
      // '/assets/images/foods/nigerianfood16.jpg',
      // '/assets/images/foods/nigerianfood17.jpg',
      // '/assets/images/foods/nigerianfood18.jpg',
      // '/assets/images/foods/nigerianfood19.jpg',
      // '/assets/images/foods/nigerianfood20.jpg',
      // '/assets/images/foods/nigerianfood21.jpg',
      // '/assets/images/foods/nigerianfood22.jpg',
      // '/assets/images/foods/ugbanija.jpg',
      // '/assets/images/foods/Ugbanigerianfood.jpeg',
      // '/assets/images/foods/nigerian14',

    ]

  }
}
