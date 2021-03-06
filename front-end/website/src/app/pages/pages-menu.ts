import {NbMenuItem} from '@nebular/theme';

export const MENU_ITEMS_ADMIN: NbMenuItem[] = [

  {
    title: 'Agendas',
    icon: 'calendar-outline',
    link: '/pages/calendrier/liste',
  },
  {
    title: 'Documentation',
    icon: 'folder-outline',
    link: '/pages/documents/liste',
  },
  {
    title: 'Groupes de diffusions',
    icon: 'email-outline',
    link: '/pages/gpdiffusions/tabs',
  },
  {
    title: 'Trombinoscope',
    icon: 'book-open',
    link: '/pages/trombinoscope/liste',
  },
  {
    title: 'ADMINISTRATION',
    group: true,
  },
  {
    title: 'Saisons',
    icon: 'clock',
    link: '/pages/saisons/liste',
  },
  {
    title: 'Adhérents',
    icon: 'person',
    link: '/pages/adhsaison/liste',
  },


];

export const MENU_ITEMS_ADH: NbMenuItem[] = [

  {
    title: 'Agendas',
    icon: 'calendar-outline',
    link: '/pages/calendrier/liste',
  },
  {
    title: 'Documentation',
    icon: 'folder-outline',
    link: '/pages/documents/liste',
  },
  {
    title: 'Groupes de diffusions',
    icon: 'email-outline',
    link: '/pages/gpdiffusions/tabs',
  },
  {
    title: 'Trombinoscope',
    icon: 'book-open',
    link: '/pages/trombinoscope/liste',
  },
];
