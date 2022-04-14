<?php

namespace App\Controller;

use App\Entity\Produit;
use App\Form\ProduitType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

class ProduitController extends AbstractController
{
    /**
     * @Route("/", name="app_produit")
     */
    public function index(): Response
    {$produits= $this->getDoctrine()->getManager()->getRepository(Produit::class)->findAll();
        return $this->render('produit/index.html.twig', ['p'=>$produits
        ]);
    }

     /**
     * @Route("/produit/addPdt", name="add_produit")
     */
    public function addPdt(Request $request): Response
    {
       $prod= new Produit;
       $form=$this-> createForm(ProduitType::class, $prod);
       $form-> handleRequest($request);

       if($form->isSubmitted() && $form->isValid()){
           $em= $this->getDoctrine()->getManager();
           $em->persist($prod);
           $em->flush();
           return $this->redirectToRoute('app_produit');

       }
       return $this->render('produit/create.html.twig', [
        'f' => $form->createView() ]);
    }

     /**
     * @Route("/suppPdt/{id}", name="delete_produit")
     */
    public function delete(Produit $prod): Response
    {
        $em= $this->getDoctrine()->getManager();
        $em->remove($prod);
        $em->flush();
        return $this->redirectToRoute('app_produit');
    }

     /**
     * @Route("/modifPdt/{id}", name="update_produit")
     */
    public function update(Request $request,$id): Response
    {
        $produit = $this->getDoctrine()->getManager()->getRepository(Produit::class)->find($id);

        $form = $this->createForm(ProduitType::class,$produit);

        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();

            return $this->redirectToRoute('app_produit');
        }
        return $this->render('produit/update.html.twig',['f'=>$form->createView()]);




    }
}
