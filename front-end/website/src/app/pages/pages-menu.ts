import {NbMenuItem} from '@nebular/theme';

/**
 * Menu pour le profil adhérent
 */
export const MENU_ITEMS_ADH: NbMenuItem[] = [

  {
    title: 'Tableau de bord',
    icon: 'home-outline',
    home: true,
    link: '/pages/dashboard',
  },
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


/**
 * Menu responsable des ateliers
 */
export const MENU_ITEMS_EVENEMENTS: NbMenuItem[] = [
  {
    title: 'Tableau de bord',
    icon: 'home-outline',
    home: true,
    link: '/pages/dashboard',
  },
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
    title: 'EVENEMENTS',
    group: true,
  },
  {
    title: 'Synthèse participations',
    icon: 'clock',
    link: '/pages/calendrier/syntheseparticipation',
  },
];





/**
 * Menu pour le conseil d'admin et le bureau
 */
export const MENU_ITEMS_CONSEIL: NbMenuItem[]  = [
  {
    title: 'Tableau de bord',
    icon: 'home-outline',
    home: true,
    link: '/pages/dashboard',
  },
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
    title: 'EVENEMENTS',
    group: true,
  },
  {
    title: 'Synthèse participations',
    icon: 'clock',
    link: '/pages/calendrier/syntheseparticipation',
  },


  {
    title: 'FICHIER ADHERENTS',
    group: true,
  },
  {
    title: 'Adhérents',
    icon: 'person',
    link: '/pages/adhsaison/liste',
  },
];



export const MENU_ITEMS_ADMIN: NbMenuItem[] = [
  {
    title: 'Tableau de bord',
    icon: 'home-outline',
    home: true,
    link: '/pages/dashboard',
  },
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
    title: 'EVENEMENTS',
    group: true,
  },
  {
    title: 'Synthèse participations',
    icon: 'clock',
    link: '/pages/calendrier/syntheseparticipation',
  },


  {
    title: 'FICHIER ADHERENTS',
    group: true,
  },
  {
    title: 'Adhérents',
    icon: 'person',
    link: '/pages/adhsaison/liste',
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

];


