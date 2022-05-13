<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Form\CategorieType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

class CategorieController extends AbstractController
{
    /**
     * @Route("/categorie", name="app_categorie")
     */
    public function index(): Response
    {$categories= $this->getDoctrine()->getManager()->getRepository(Categorie::class)->findAll();
        return $this->render('categorie/index.html.twig', ['c'=>$categories
        ]);
    }
    

    /**
     * @Route("/categorie/addCat", name="add_categorie")
     */
    public function addCat(Request $request): Response
    {
        $cat= new Categorie;
        $form=$this-> createForm(CategorieType::class, $cat);
        $form-> handleRequest($request);
 
        if($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->persist($cat);
            $em->flush();

            $this->addFlash(
                'info',
                'categorie ajouter avec success!'
            );

            return $this->redirectToRoute('app_categorie');
 
        }
        return $this->render('categorie/create.html.twig', [
         'f' => $form->createView() ]);
    }

    /**
     * @Route("/modifCat/{id}", name="update_categorie")
     */
    public function update(Request $request,$id): Response
    {
        $categorie = $this->getDoctrine()->getManager()->getRepository(Categorie::class)->find($id);

        $form = $this->createForm(CategorieType::class,$categorie);

        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();

            return $this->redirectToRoute('app_categorie');
        }
        return $this->render('categorie/update.html.twig',['f'=>$form->createView()]);


    }
     /**
     * @Route("/suppCat/{id}", name="delete_categorie")
     */
    public function delete(Categorie $cat): Response
    {
        $em= $this->getDoctrine()->getManager();
        $em->remove($cat);
        $em->flush();
        return $this->redirectToRoute('app_categorie');
    }

     /**
     * @Route("/produit/add/", name="app_produit")
     */
    public function addPdt(Request $request): Response
    { return $this->render('produit/create.html.twig');
    }
}

