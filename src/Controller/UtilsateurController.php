<?php

namespace App\Controller;

use App\Entity\Utilisateur;
//use App\Repository\ClassroomRepository;
use App\Form\UtilisateurType;
use App\Controller\Request;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class UtilsateurController extends AbstractController
{


 /**
     * @Route("/utilisateur/add", name="page")
     */
    public function page(): Response
    {
        
        return $this->render('utilsateur/add.html.twig'
        ); 
    }

}
