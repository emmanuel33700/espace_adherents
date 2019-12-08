import {NbMenuItem} from '@nebular/theme';

export const MENU_ITEMS: NbMenuItem[] = [

  {
    title: 'Documentation',
    icon: 'folder-outline',
    link: '/pages/documents/liste',
  },
  {
    title: 'Agendas',
    icon: 'calendar-outline',
    link: '/pages/agendas/liste',
  },
  {
    title: 'Groupes de diffusions',
    icon: 'email-outline',
    link: '/pages/gpdiffusions/liste',
  },
  {
    title: 'Trombinoscope',
    icon: 'book-open',
    link: '/pages/trombinoscope/liste',
  },
  {
    title: 'ADMINISTRATION ADHESIONS',
    group: true,
  },

  {
    title: 'Les saisons',
    icon: 'clock',
    link: '/pages/saisons/liste',
  },
  {
    title: 'Adhérent de la saison',
    icon: 'person',
    link: '/pages/adhsaison/liste',
  },
  {
    title: 'Adhérent enregistrés',
    icon: 'people',
    link: '/pages/adhenregistres/liste',
  },

];
